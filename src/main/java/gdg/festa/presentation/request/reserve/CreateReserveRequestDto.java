package gdg.festa.presentation.request.reserve;

public record CreateReserveRequestDto(
        String browserToken,
        String phoneNumber,
        String name,
        Long attendance
) {
}
