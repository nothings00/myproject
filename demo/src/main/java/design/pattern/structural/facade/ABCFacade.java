package design.pattern.structural.facade;

/**
 * 系统ABC类
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-01 10:55
 * @version 1.0.0
 */
public class ABCFacade implements Facade{

    SystemA systemA;
    SystemB systemB;
    SystemC systemC;

    public ABCFacade(SystemA systemA, SystemB systemB, SystemC systemC) {
        this.systemA = systemA;
        this.systemB = systemB;
        this.systemC = systemC;
    }

    @Override
    public void wrapOperaiont() {
        systemA.operationA();
        systemB.operationB();
        systemC.operationC();
    }
}
