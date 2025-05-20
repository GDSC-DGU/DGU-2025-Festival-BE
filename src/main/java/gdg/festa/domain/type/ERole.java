package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
    //USER("USER", "ROLE_USER"),
    ADFESTA("ADFESTA", "ROLE_AD_FESTA"),
    ADPUB("ADPUB", "ROLE_ADPUB");

    private final String name;
    private final String securityName;
}
