### 1. javadoc抽取信息
* 包
* 公有类和接口
* 公有的和受保护的构造和方法
* 公有的和受保护的域

### 2. 格式
```
/**
* 一句话介绍
* 通用标记：
  @author 作者*n
  @version 版本
  @since  始于
  @decrecated 不再使用
  @see 超级链接*n，#表示方法或变量 
       eg: @see com.hello#say(String)
       eg: @see <a href="http://www.baidu.com">百度</a>
       eg: @see "提示内容"
  @link 超级链接*n eg:{ @link package.class#feature label } 语法同@see
  方法标记：
  @param 变量描述
  @return 描述
  @throws 类描述
  ]
* <em> 强调 </em> 
* <strong> 着重强调 </strong>
* <img src="图片url" alt="提示语">
* {@Code 等宽代码}
*/
```

### 3. 位置
1. 类注释必须放在 import 语句之后 ， 类定义之前
2. 方法注释 每一个方法注释必须放在所描述的方法之前
3. 域注释 只需要对公有域 （ 通常指的是静态常量 ） 建立文档

### 4.标记
```

```