package gdg.festa.presentation.request;

public record LostsRequestDto(
        String title,
        String color,
        String brand,
        String location,
        String note,
        String tag,
        String CategoryName
) {
}
