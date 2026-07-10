import java.util.*;

/** Solutions for Algorithms_Data Structures.docx. */
public class AlgorithmsAndDataStructures {
  record Product(int id, String name, String category, int quantity, double price) {}
  record Order(int id, String customer, double totalPrice) {}
  record Employee(int id, String name, String position, double salary) {}
  record Book(int id, String title, String author) {}
  record Task(int id, String name, String status) {}

  // Exercise 1: HashMap gives expected O(1) add, update, delete and lookup.
  static final class Inventory {
    private final Map<Integer, Product> products = new HashMap<>();
    void add(Product p) { if (products.putIfAbsent(p.id(), p) != null) throw new IllegalArgumentException("Duplicate id"); }
    void update(Product p) { if (products.replace(p.id(), p) == null) throw new NoSuchElementException("Unknown product"); }
    void delete(int id) { products.remove(id); }
    Product find(int id) { return products.get(id); }
  }

  // Exercise 2. Linear search is O(n); binary search is O(log n), after O(n log n) sorting.
  static Product linearSearch(Product[] a, String name) {
    for (Product p : a) if (p.name().equalsIgnoreCase(name)) return p;
    return null;
  }
  static Product binarySearch(Product[] sorted, String name) {
    int lo = 0, hi = sorted.length - 1;
    while (lo <= hi) { int mid = (lo + hi) >>> 1; int c = sorted[mid].name().compareToIgnoreCase(name);
      if (c == 0) return sorted[mid]; if (c < 0) lo = mid + 1; else hi = mid - 1; }
    return null;
  }

  // Exercise 3. Bubble sort is O(n^2); quicksort averages O(n log n), worst O(n^2).
  static void bubbleSort(Order[] a) {
    for (int end = a.length - 1; end > 0; end--) { boolean changed = false;
      for (int i = 0; i < end; i++) if (a[i].totalPrice() > a[i + 1].totalPrice()) {
        Order t = a[i]; a[i] = a[i + 1]; a[i + 1] = t; changed = true; }
      if (!changed) return;
    }
  }
  static void quickSort(Order[] a, int lo, int hi) {
    if (lo >= hi) return; double pivot = a[(lo + hi) >>> 1].totalPrice(); int i = lo, j = hi;
    while (i <= j) { while (a[i].totalPrice() < pivot) i++; while (a[j].totalPrice() > pivot) j--;
      if (i <= j) { Order t = a[i]; a[i++] = a[j]; a[j--] = t; } }
    quickSort(a, lo, j); quickSort(a, i, hi);
  }

  // Exercise 4. Array access is O(1); add/search/delete are O(n), traversal O(n).
  static final class Employees {
    private Employee[] values = new Employee[4]; private int size;
    void add(Employee e) { if (size == values.length) values = Arrays.copyOf(values, size * 2); values[size++] = e; }
    Employee search(int id) { for (int i=0;i<size;i++) if(values[i].id()==id)return values[i]; return null; }
    void delete(int id) { for(int i=0;i<size;i++) if(values[i].id()==id) { System.arraycopy(values,i+1,values,i,size-i-1); values[--size]=null; return; } }
    List<Employee> traverse() { return Arrays.asList(Arrays.copyOf(values, size)); }
  }

  // Exercise 5. Linked list insertion at head is O(1); id search/delete/traversal are O(n).
  static final class TaskList {
    private static final class Node { Task value; Node next; Node(Task v){value=v;} } Node head;
    void add(Task t) { Node n=new Node(t); n.next=head; head=n; }
    Task search(int id) { for(Node n=head;n!=null;n=n.next) if(n.value.id()==id)return n.value; return null; }
    void delete(int id) { Node prev=null, cur=head; while(cur!=null) { if(cur.value.id()==id) { if(prev==null)head=cur.next; else prev.next=cur.next; return;} prev=cur;cur=cur.next;} }
    void traverse() { for(Node n=head;n!=null;n=n.next) System.out.println(n.value); }
  }

  // Exercise 6. Same algorithms: linear O(n), binary O(log n) on title-sorted data.
  static Book findBookLinear(Book[] books, String title) { for(Book b:books) if(b.title().equalsIgnoreCase(title))return b; return null; }
  static Book findBookBinary(Book[] books, String title) { int lo=0,hi=books.length-1; while(lo<=hi){int m=(lo+hi)>>>1;int c=books[m].title().compareToIgnoreCase(title);if(c==0)return books[m];if(c<0)lo=m+1;else hi=m-1;}return null; }

  // Exercise 7. Recurrence FV(n)=FV(n-1)*(1+rate[n-1]); O(n) time/stack, or use a loop to avoid stack use.
  static double futureValue(double current, double[] yearlyRates, int years) {
    if (years == 0) return current;
    return futureValue(current, yearlyRates, years - 1) * (1 + yearlyRates[years - 1]);
  }
  public static void main(String[] args) {
    Product[] products={new Product(1,"Camera","Electronics",5,499),new Product(2,"Laptop","Electronics",2,900)};
    Arrays.sort(products, Comparator.comparing(Product::name));
    System.out.println(binarySearch(products,"Laptop"));
    System.out.println("Forecast: " + futureValue(1000,new double[]{.05,.04,.06},3));
  }
}
