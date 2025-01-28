package dto;

public record BottomDetailInfo(
        String name,
        char size,
        int length,
        double waist,
        double rise,
        double thigh,
        double hem,
        int quantity,
        int price
) implements ProductDetailInfo {

    @Override
    public String getDetail() {
        StringBuffer sb = new StringBuffer();

        sb.append("사이즈 - ").append(size).append("\n");
        sb.append("기장 - ").append(length).append("cm").append("\n");
        sb.append("허리둘레 - ").append(waist).append("cm").append("\n");
        sb.append("밑위 - ").append(rise).append("cm").append("\n");
        sb.append("허벅지 - ").append(thigh).append("cm").append("\n");
        sb.append("밑단 - ").append(hem).append("cm").append("\n");
        sb.append("남은 수량 - ").append(quantity).append("개").append("\n");

        return sb.toString();
    }
}
