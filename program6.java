import java.util.Scanner;

public class program6 {
    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        List stringTest = new List();
        List groceryList1 = new List();
        List groceryList2 = new List();
        List choice = null;
        int list = 0;
        int select = 0;

        while (list != 4) {

            System.out.println("Select the list you would like to view: ");
            System.out.println("(1) List of Strings: ");
            System.out.println("(2) Grocery List #1: ");
            System.out.println("(3) Grocery List #2: ");
            System.out.println("(4) Exit program");
            list = kb.nextInt();
        
            if (list == 1) {
                choice = stringTest;
            }
            else if (list == 2) {
                choice = groceryList1;
            }
            else if (list == 3) {
                choice = groceryList2;
            }
        
            while (select != 9 && list != 4) {
                select = menu();
                if (select == 1) {
                    String data;
                    int pos;
                    if (choice == stringTest) {
                        System.out.println("Enter the String you would like to add to the List: ");
                        data = kb.next();
                        System.out.println("Enter the position to place the item: ");
                        pos = kb.nextInt();
                        choice.insert(data, pos);
                        System.out.println("Item inserted at position " + pos);
                    }
                    else {
                        double cost;
                        int quantity;
                        GroceryItem item;
                        System.out.println("Enter the name of the grocery item to add to the list: ");
                        data = kb.next();
                        System.out.println("Enter the cost of the grocery item: ");
                        cost = kb.nextDouble();
                        System.out.println("Enter the # of items of this grocery item: ");
                        quantity = kb.nextInt();
                        item = new GroceryItem(data, cost, quantity);
                        System.out.println("Enter the position to place the item: ");
                        pos = kb.nextInt();
                        choice.insert(item, pos);
                        System.out.println("Item inserted at position " + pos);
                    }
                }
                else if (select == 2) {
                    String data;
                    if (choice == stringTest) {
                        System.out.println("Enter the String you would like to add to the front of the List: ");
                        data = kb.next();
                        choice.insertAtFront(data);
                        System.out.println("Item inserted at front of list");
                    }
                    else {
                        double cost;
                        int quantity;
                        GroceryItem item;
                        System.out.println("Enter the name of the grocery item to add to the front of the list: ");
                        data = kb.next();
                        System.out.println("Enter the cost of the grocery item: ");
                        cost = kb.nextDouble();
                        System.out.println("Enter the # of items of this grocery item: ");
                        quantity = kb.nextInt();
                        item = new GroceryItem(data, cost, quantity);
                        choice.insertAtFront(item);
                        System.out.println("Item inserted at front of list");
                    }
                }
                else if (select == 3) {
                    int start, end;
                    System.out.println("Enter the range of positions you would like to remove from the list: ");
                    System.out.println("Start position: ");
                    start = kb.nextInt();
                    System.out.println("End position: ");
                    end = kb.nextInt();
                    choice.deleteRange(start, end);
                    System.out.println("List items deleted from position " + start + " to position " + end);
                }
                else if (select == 4) {
                    String item;
                    System.out.println("Enter the name of the item you would like to delete all occurences of: ");
                    item = kb.next();
                    if (choice == stringTest) {
                        choice.deleteItem(item);
                    }
                    else {
                        GroceryItem delItem = new GroceryItem(item, 0, 0);
                        choice.deleteItem(delItem);
                    }
                    System.out.println("All occurences of \"" + item + "\" deleted from list");
                }
                else if (select == 5) {
                    int pos;
                    System.out.println("Enter the position in the list you wold like to view: ");
                    pos = kb.nextInt();
                    System.out.println(choice.retrieve(pos));
                }
                else if (select == 6) {
                    String item;
                    System.out.println("Enter the name of the item to view all positions of each occurance: ");
                    item = kb.next();
                    if (choice == stringTest) {
                        System.out.println(choice.find(item));
                    }
                    else {
                        GroceryItem findItem = new GroceryItem(item, 0, 0);
                        System.out.println(choice.find(findItem));
                    }
                }
                else if (select == 7) {
                    System.out.println("List size: " + choice.getSize() + " items");
                }
                else if (select == 8) {
                    System.out.println(choice);
                }
            }
            
        }
    }

    public static int menu() {
        int select;
        System.out.println("\n=== MENU ===");
        System.out.println("Select the operation you would like to perform: ");
        System.out.println("(1) Insert an item at a chosen position: ");
        System.out.println("(2) Insert an item at the front: ");
        System.out.println("(3) Delete all items in a range of positions: ");
        System.out.println("(4) Delete all occurances of a chosen item: ");
        System.out.println("(5) Retrieve the item at a chosen position: ");
        System.out.println("(6) Find all positions where an item is located: ");
        System.out.println("(7) Get the size of the list: ");
        System.out.println("(8) Display the entire list: ");
        System.out.println("(9) Return to list selection: ");

        select = kb.nextInt();

        return select;
    }
    
}

class List {
    private Link _head;
    private int _size;

    public List() {
        _head = null;
        _size = 0;
    }

    public void insert(Object data, int pos) {
        Link point, follow;
        Link addLink = new Link();
        int p = 1;

        addLink._data = data;
        point = _head;
        follow = null;
        
        if(pos == 1) {
            insertAtFront(data);
            return;
        }

        if (pos <= _size + 1 && pos > 1) {
            while (point._next != null) {
                if (p == pos) {
                    addLink._next = point;
                    follow._next = addLink;
                    ++_size;
                    return;
                }
                follow = point;
                point = point._next;
                ++p;
            }
            if (point._next == null) {
                point._next = addLink;
                addLink._next = null;
                ++_size;
                return;
            }
        }
    }

    public void insertAtFront(Object data) {
        Link addLink = new Link();
        addLink._data = data;
        addLink._next = _head;
        _head = addLink;
        ++_size;
    }

    public void deleteRange(int start, int end) {
        Link point, follow, jump;
        int p = 1;
        point = _head;
        follow = _head;
        jump = null;
        while (point != null) {
            follow = point;
            point = point._next;
            ++p;
            if (p == start) {
                jump = follow;
            }
            if (p == end) {
                jump._next = point._next; 
            }
        }
    }

    public void deleteItem(Object item) {
        Link point, follow;
        point = _head;
        follow = _head;
        while (point != null) {
            if (point._data.equals(item)) {
                follow._next = point._next;
                --_size;
            }
            follow = point;
            point = point._next;
        }
    }

    public Object retrieve(int pos) {
        Link point;
        int p = 1;
        point = _head;
        while (point != null) {
            if (p == pos) {
                return point._data;
            }
            point = point._next;
            ++p;
        }
        return null;
    }

    public String find(Object data) {
        String s = "";
        Link point;
        int p = 1;

        s += "\"" + data + "\" found at positions: ";
        point = _head;
        while (point != null) {
            if (point._data.equals(data)) {
                s += "\n" + p;
            }
            point = point._next;
            ++p;
        }
        return s;
    }

    public int getSize() {
        return _size;
    }

    public String toString() {
        String s = "";
        s += "\nList Contents\n";
        Link point = _head;
        while (point != null) {
            s += point._data + "\n";
            point = point._next;
        }
        return s;
    }

}

class Link {
    public Object _data;
    public Link _next;
}

class GroceryItem {
    private String _name;
    private double _cost;
    private int _quantity;

    public GroceryItem(String name, double cost, int quantity) {
        _name = name;
        _cost = cost;
        _quantity = quantity;
    }

    public String getName() {
        return _name;
    }

    public double getCost() {
        return _cost;
    }

    public int getQuantity() {
        return _quantity;
    }

    public boolean equals(Object other) {
        if (other instanceof GroceryItem) {
            GroceryItem otherItem = (GroceryItem) other;
            return (_name.equals(otherItem._name));
        }
        else {
            return false;
        }
    }

    public String toString() {
        String s = "";
        s += "\nName: " + _name;
        s += "\nCost: $" + _cost;
        s += "\nQuantity: " + _quantity;
        return s;
    }

}