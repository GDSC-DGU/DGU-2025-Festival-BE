package gdg.festa.presentation.request.reserve;

public record CreateReserveRequestDto(
//        String browserToken, => 이거땜에 에러나는데 확인해주삼
        String phoneNumber,
        String name,
        Long attendance
) {
}
