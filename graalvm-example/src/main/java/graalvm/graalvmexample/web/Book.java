package graalvm.graalvmexample.web;

public record Book(
        String isbn,
        String name,
        int price
) {
}
