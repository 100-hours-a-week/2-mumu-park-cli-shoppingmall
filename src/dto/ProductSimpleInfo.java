package dto;

public record ProductSimpleInfo(
        String name,
        char size,
        int quantity,
        int price
) {
}
