package typewise_alert.utils;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Utility class helps obtaining classes and object instance.
 *
 * @author {@literal Jayaram Naveenkumar (jayaram.naveenkumar@in.bosch.com)}
 */
public final class ClassHandler {

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final MethodType CONSTRUCTOR_TYPE = MethodType.methodType(void.class);

    private ClassHandler() {
    }

    /**
     * Return all the classes under package name argument
     *
     * @param <T>         class of type T
     * @param packageName name of the package
     * @return array of classes
     *
     * @throws ClassNotFoundException throws Exception
     * @throws IOException            throws Exception
     */
    public static <T> Class<T>[] getClasses(String packageName)
          throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[0]);
    }

    private static List<Class<?>> findClasses(
          File directory,
          String packageName
    ) throws ClassNotFoundException
    {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + '.' + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(
                      packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    /**
     * Return instance of object based on Class argument
     *
     * @param className Class name on which instance is created
     * @param <T>       object of type T
     * @return Object instance of class
     *
     * @throws Throwable throws Exception
     */
    public static <T> Object getInstanceForNoArgConstructor(Class<T> className) throws Throwable
    {
        return LOOKUP.findConstructor(className, CONSTRUCTOR_TYPE).invoke();
    }
}
