/**
 * @author : can
 * create at:  2021/12/19  14:30
 * @description: select伪代码
 */
class IO {

    /**
     * fd_set是long 的数组。实际上是一个long类型的数组，
     * 每一位代表一个对应的文件描述符，通过宏进行添加和删除。当调用select()时，由内核根据IO状态修改fd_set的内容，由此来返回那些就绪IO。
     * 因为 select 函数是作为 POSIX 标准中的系统调用，在不同版本的操作系统上都会实现，所以将其作为保底方案
     */
    class fd_set{
        long val;
    }
    /**select()缺点就是调用该函数，内核会把fd_set 从用户态拷贝到内核态。开销大，而且内核会设定最大集合大小，一般为1024，也就是一个线程最多监听1024个
     *fd_set是long 的数组。
     * @param maxId 监听个数
     * @param readset 监听读集合
     * @param writeset 监听写集合
     * @param errorset 错误集合
     * @param timeout  等待时间
     * @return 返回值：有就绪描述符就返回其数目，若超时则为0，若出错则为-1
     */
    int select(int maxId,fd_set[] readset,fd_set[] writeset ,fd_set[] errorset,int timeout){
        return -1;
    }

    /**
     * 基于链表实现，所以无最大长度。但是大量的fd的数组被整体复制到用户态和内核空间之间，线性轮询，消耗太大。
     * @return
     */
    int poll(){
        return -1;
    }

    /**
     * 双向链表
     */
    void epoll  (){}
}
