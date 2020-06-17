package core.concurrent.thread;

import lombok.Data;

import java.util.Map;

@Data
public class ThreadHolder {
    ThreadLocal<String> name = new ThreadLocal<>();
    ThreadLocal<Long> id = new ThreadLocal<>();
    ThreadLocal<Map<String,Object>> user = new ThreadLocal<>();

    public ThreadHolder(Map<String,Object> user) {
        name.set(Thread.currentThread().getName());
        id.set( Thread.currentThread().getId());
        this.user.set(user);
        System.out.println("name="+name.get()+",id="+id.get()+","+user);
    }
}
