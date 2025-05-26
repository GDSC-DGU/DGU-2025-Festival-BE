package gdg.festa.presentation.request.reserve;

public record CreateReserveRequestDto(
        String phoneNumber,
        String name,
        Long attendance
) {
}
