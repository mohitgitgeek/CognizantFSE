import java.util.*;

/** Compact runnable solutions for Design Patterns and Principles.docx. */
public class DesignPatternsAndPrinciples {
  // 1 Singleton: private constructor + volatile instance makes it safe across threads.
  static final class Logger { private static final Logger INSTANCE=new Logger(); private Logger(){} static Logger getInstance(){return INSTANCE;} void log(String s){System.out.println(s);} }
  // 2 Factory Method
  interface Document { String open(); } record WordDocument() implements Document {public String open(){return "Word";}} record PdfDocument() implements Document {public String open(){return "PDF";}} record ExcelDocument() implements Document {public String open(){return "Excel";}}
  abstract static class DocumentFactory { abstract Document createDocument(); } static final class PdfFactory extends DocumentFactory { Document createDocument(){return new PdfDocument();} }
  // 3 Builder
  static final class Computer { final String cpu,ram,storage; private Computer(Builder b){cpu=b.cpu;ram=b.ram;storage=b.storage;} static class Builder {String cpu,ram,storage; Builder cpu(String v){cpu=v;return this;} Builder ram(String v){ram=v;return this;} Builder storage(String v){storage=v;return this;} Computer build(){return new Computer(this);} } }
  // 4 Adapter
  interface PaymentProcessor { void processPayment(double amount); } static final class LegacyGateway {void chargeCents(long cents){System.out.println("Charged "+cents+" cents");}} static final class GatewayAdapter implements PaymentProcessor {final LegacyGateway gateway=new LegacyGateway();public void processPayment(double a){gateway.chargeCents(Math.round(a*100));}}
  // 5 Decorator
  interface Notifier {void send(String text);} static final class EmailNotifier implements Notifier {public void send(String t){System.out.println("Email: "+t);}} static abstract class NotifierDecorator implements Notifier {final Notifier next; NotifierDecorator(Notifier n){next=n;}} static final class SmsNotifier extends NotifierDecorator {SmsNotifier(Notifier n){super(n);}public void send(String t){next.send(t);System.out.println("SMS: "+t);}} static final class SlackNotifier extends NotifierDecorator {SlackNotifier(Notifier n){super(n);}public void send(String t){next.send(t);System.out.println("Slack: "+t);}}
  // 6 Proxy
  interface Image {void display();} static final class RealImage implements Image {final String file; RealImage(String f){file=f;System.out.println("Loading "+f);}public void display(){System.out.println("Displaying "+file);}} static final class ProxyImage implements Image {final String file; RealImage real; ProxyImage(String f){file=f;}public void display(){if(real==null)real=new RealImage(file);real.display();}}
  // 7 Observer
  interface Observer {void update(String symbol,double price);} static final class StockMarket {final List<Observer> observers=new ArrayList<>();void register(Observer o){observers.add(o);}void deregister(Observer o){observers.remove(o);}void setPrice(String s,double p){observers.forEach(o->o.update(s,p));}} static final class MobileApp implements Observer {public void update(String s,double p){System.out.println("Mobile "+s+": "+p);}}
  // 8 Strategy
  interface PaymentStrategy {void pay(double amount);} static final class CreditCardPayment implements PaymentStrategy {public void pay(double a){System.out.println("Card payment "+a);}} static final class PayPalPayment implements PaymentStrategy {public void pay(double a){System.out.println("PayPal payment "+a);}} static final class PaymentContext {PaymentStrategy strategy;PaymentContext(PaymentStrategy s){strategy=s;}void pay(double a){strategy.pay(a);}}
  // 9 Command
  interface Command {void execute();} static final class Light {void on(){System.out.println("Light on");}void off(){System.out.println("Light off");}} static final class LightOnCommand implements Command {final Light l;LightOnCommand(Light l){this.l=l;}public void execute(){l.on();}} static final class LightOffCommand implements Command {final Light l;LightOffCommand(Light l){this.l=l;}public void execute(){l.off();}} static final class RemoteControl {Command command;void setCommand(Command c){command=c;}void press(){command.execute();}}
  // 10 MVC
  static final class Student {String name,grade;final int id;Student(int id,String n,String g){this.id=id;name=n;grade=g;}} static final class StudentView {void displayStudentDetails(Student s){System.out.printf("%d %s %s%n",s.id,s.name,s.grade);}} static final class StudentController {final Student model;final StudentView view;StudentController(Student m,StudentView v){model=m;view=v;}void setGrade(String g){model.grade=g;}void updateView(){view.displayStudentDetails(model);}}
  // 11 Dependency Injection
  record Customer(int id,String name){} interface CustomerRepository {Customer findCustomerById(int id);} static final class CustomerRepositoryImpl implements CustomerRepository {public Customer findCustomerById(int id){return new Customer(id,"Customer "+id);}} static final class CustomerService {final CustomerRepository repo;CustomerService(CustomerRepository r){repo=r;}Customer findCustomer(int id){return repo.findCustomerById(id);}}
  public static void main(String[] args) { Logger.getInstance().log("one logger"); System.out.println(new PdfFactory().createDocument().open()); new SlackNotifier(new SmsNotifier(new EmailNotifier())).send("Hello"); new ProxyImage("logo.png").display(); new PaymentContext(new PayPalPayment()).pay(99); }
}
