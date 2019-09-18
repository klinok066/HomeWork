public class Main1 {

    public static void main(String[] args){
        makeArray();

    }

    static void makeArray(){
        int [] array = new int[12];
        for(int i = 0; i < 12; i++) {
                array[i] = (int)(Math.random() * ((15 + 15 ))- 15);
            }
        int number = 0;
        int max = -16;
        for(int i = 0; i < 12; i++) {
            if(array[i] > max){
                max = array[i];
                number = i;
            }
        }
        System.out.println(number);
    }
}
