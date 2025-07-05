package Java2.Luyen_oop_bankmanage;

import java.util.Scanner;

public class BankAccount {

    public static final int GET_INFO = 1; //In ra thông tin
    public static final int WITHDRAW = 2; //Rút tiền
    public static final int RENAME = 3; //Đổi tên
    public static final int DEPOSIT = 4; //Nạp tiền
    public static final int EXIST = 5; //Thoát

    //Thuộc tính
    private String accountNumber; //Mã tài khoản
    private String accountHolderName; //Tên người dùng
    private double balance; //Số dư

    //Phương thức
    public BankAccount(){
        accountNumber = "NONE";
        accountHolderName = "NONE";
        balance = 0.0;
    }

    public BankAccount(String accountNumber, String accountHolderName, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getNumber(){
        return accountNumber;
    }

    public String getHolderName(){
        return accountHolderName;
    }

    public double getBalance(){
        return balance;
    }

    private void setHolderName(String accountHolderName){
        this.accountHolderName = accountHolderName;
    }

    private boolean addBalance(double amount){
        if (amount < 0) return false;
        balance += amount; 
        return true;
    }

    private int withdraw(double amount){
        if (amount < 0) return -1;
        if (amount > balance) return 1;
        balance -= amount;
        return 0;
    }

    //Làm việc với người dùng
    public void getInfo(){ //Lấy thông tin
        System.out.println("========== INFO ==========");
        System.out.println("Name: " + accountHolderName);
        System.out.println("Number: " + accountNumber);
        System.out.println("Balance: " + balance + "$");
        System.out.println("==========================");
        System.out.println();
    }

    public void doWithdraw(Scanner sc){ //Rút tiền
        int TRY = 0;
        while (true){
            if (TRY > 0) System.out.println("Try Again !");
            System.out.print("Enter amount you want withdraw: "); double am = sc.nextDouble();
            System.out.println();
            if (withdraw(am) == 0){
                TRY = 0;
                System.out.println("Withdraw Success !");
                System.out.println();
                break;
            }
            else if (withdraw(am) == -1) {
                TRY = 1;
                System.out.println("Invalid amount");
            }
            else {
                TRY = 1;
                System.out.println("Insufficient balance");
            }
            System.out.println();
        }
    }

    public void rename(Scanner sc){ //Đổi tên
        sc.nextLine();
        System.out.print("Enter your new name: "); String newName = sc.nextLine();
        setHolderName(newName);
        System.out.println();
        System.out.println("Rename Success !");
        System.out.println();
    }

    public void deposit(Scanner sc){ //Nạp tiền
        int TRY = 0;
        while (true){
            if (TRY > 0) System.out.println("Try Again !");
            System.out.print("Enter your amount to deposit: "); int amount = sc.nextInt();
            System.out.println();
            if (addBalance(amount)){
                TRY = 0;
                System.out.println("Deposit Success !");
                System.out.println();
                break;
            }
            else{
                TRY = 1;
                System.out.println("Invalid amount");
            }
            System.out.println();
        }
    }

    public void work(){
        Scanner sc = new Scanner(System.in);
        boolean isDone = false;
        while (!isDone){
            System.out.println("Here are the options: ");
            System.out.println("1. Get your info");
            System.out.println("2. Withdraw money");
            System.out.println("3. Change your name");
            System.out.println("4. Deposit money");
            System.out.println("5. Exist");
            System.out.print("Your choice: "); int user_choice = sc.nextInt();
            System.out.println();
            if (user_choice == EXIST) isDone = true;
            else if (user_choice == GET_INFO) getInfo();
            else if (user_choice == WITHDRAW) doWithdraw(sc);
            else if (user_choice == RENAME) rename(sc);
            else if (user_choice == DEPOSIT) deposit(sc);
        }
        sc.close();
    }
}
