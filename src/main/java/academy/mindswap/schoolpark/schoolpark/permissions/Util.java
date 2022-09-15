package academy.mindswap.schoolpark.schoolpark.permissions;

public class Util {
    public static final String VIEWER_ROLE = "hasRole('USER')";
    public static final String EDITOR_ROLE = "hasRole('ADMIN')";
    public static final String ONLY_ADMIN_AND_USER_ROLE = "hasRole('ADMIN','USER')";
    public static final String PERMIT_ALL = "permitAll()";
}
