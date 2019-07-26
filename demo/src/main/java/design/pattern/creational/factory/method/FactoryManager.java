package design.pattern.creational.factory.method;

/**
 * 工厂管理器
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-25 16:55
 * @version 1.0.0
 */
public class FactoryManager {
    public static final String FILE = "file";
    public static final String DATABASE = "database";
    public static LogFactory getLogFactory(String name){
        switch (name){
            case FILE:
                return new FileLogFactory();
            case DATABASE:
                return new DatabaseLogFactory();
            default:
                return new LogFactory() {
                    @Override
                    public Log getLog(String name) {
                        return new Log() {
                            @Override
                            public void writeLog() {
                                System.out.println("unknown log type");
                            }
                        };
                    }
                };
        }
    }
}
