package dto;

public record TopDetailInfo(
        String name,
        char size,
        int length,
        double shoulder,
        double chest,
        double sleeves,
        int quantity,
        int price
) implements ProductDetailInfo {
    @Override
    public String getDetail() {
        StringBuffer sb = new StringBuffer();

        sb.append("사이즈 - ").append(size).append("\n");
        sb.append("기장 - ").append(length).append("cm").append("\n");
        sb.append("어깨너비 - ").append(shoulder).append("cm").append("\n");
        sb.append("가슴둘레 - ").append(chest).append("cm").append("\n");
        sb.append("소매길이 - ").append(sleeves).append("cm").append("\n");
        sb.append("남은 수량 - ").append(quantity).append("개").append("\n");

        return sb.toString();
    }
}
