package tr.gov.gib.taskman.util;

public class ApiPath {
    private  static final String BASE_PATH="/api";

    public static final class  TaskCtrl{
        public  static final String CTRL=BASE_PATH+"/tasks";
    }

    public static final class  WorkitemCtrl{
        public  static final String CTRL=BASE_PATH+"/workitems";
    }

    public static final class  ProjectCtrl{
        public  static final String CTRL=BASE_PATH+"/projects";
    }

    public static final class  AuthCtrl{
        public  static final String CTRL=BASE_PATH+"/auth";
    }
}
