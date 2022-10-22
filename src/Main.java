import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main (String[] args){

    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
         for (int i = 0; i < 10_000_000; i++) {
             persons.add(new Person(
                names.get(new Random().nextInt(names.size())),
                families.get(new Random().nextInt(families.size())),
                new Random().nextInt(100),
                Sex.values()[new Random().nextInt(Sex.values().length)],
                Education.values()[new Random().nextInt(Education.values().length)])
        );
    }
        long count = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Общее количество несовершеннолетних - " + count);

        List<String> conscripts = persons.stream()
                .filter(value -> value.getAge() > 18 && value.getAge() < 27)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .limit(20)
                .collect(Collectors.toList());
        System.out.println("Пофамильный список призывников : " + conscripts);

        List<String> ablebodiedPopulation = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 60 && p.getSex() == Sex.WOMAN ||
                        p.getAge() >= 18 && p.getAge() <= 65 && p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .limit(20)
                .collect(Collectors.toList());
        System.out.println("Работоспособное население : " + ablebodiedPopulation);
    }
}