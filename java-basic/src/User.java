import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class User {

    private static final List<String> VALID_JOBS = Collections.unmodifiableList(Arrays.asList("google", "uber", "amazon"));
    private static final List<String> VALID_ADDRESSES = Collections.unmodifiableList(Arrays.asList("london", "new york", "amsterdam"));//SET???

    private String name;
    private int age;
    private String workplace;
    private String address;

    public User(String name, int age, String workplace, String address){

        if (name.isEmpty()){
            System.out.println("Name cannot be empty");
            return;
        }

        if (age < 18){
            System.out.println("Age cannot be less than 18");
            return;
        }

        if (!VALID_JOBS.contains(workplace.toLowerCase())){
            StringJoiner joiner = new StringJoiner(" ,");
            VALID_JOBS.forEach(item -> joiner.add(item));
            System.out.println("Workplace entered: " + workplace + ", but should be one of the following values: " + joiner);
            return;
        }

        if (!VALID_ADDRESSES.contains(address.toLowerCase())){
            System.out.printf("Address entered: %s, but should be one of the following values: %s%n", address, VALID_ADDRESSES.stream().collect(Collectors.joining(" ,")));
            return;
        }

//        if (!VALID_ADDRESSES.contains(address.toLowerCase())){
//            System.out.printf("Address entered: %s, but should be one of the following values: %s%n", address, String.join(" ,", VALID_JOBS));
//            return;
//        }

        this.name = name;
        this.age = age;
        this.workplace = workplace;
        this.address = address;
    }

    public int getAge(){
        return this.age;
    }

    public static Map<Integer, List<User>> groupUsers(List<User> users) {

        Map<Integer, List<User>> result =new HashMap<>();
        for (User user : users){
            if (result.containsKey(user.age)) continue;

            List<User> tmpUsers = new ArrayList<>();
            for (User user1 : users){
                if (user.age == user1.age){
                    tmpUsers.add(user1);
                }
            }
            result.put(user.age, tmpUsers);
        }

//        result = users.stream().collect(Collectors.groupingBy(User::getAge));

        return result;
    }
}