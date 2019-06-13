
namespace java com.ztwu.demo.thrift

service HelloWorldService {
  string sayHello(1:string username)
  string getRandom()
}