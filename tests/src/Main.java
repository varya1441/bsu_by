class Main {
    public static void main(String[] args) {
       int []arr=new int[10];
       System.out.println(arr.length);
       MyClass myClass=new MyClass();
      System.out.println(myClass.games());
    }
}
class MyClass{
    int play;
    MyClass(){
        play=0;
    }
    int games() {
        return play;
    }
}
