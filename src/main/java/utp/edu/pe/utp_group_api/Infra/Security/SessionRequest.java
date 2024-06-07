package utp.edu.pe.utp_group_api.Infra.Security;

public record SessionRequest(
        String username,
        String password
) {
}
