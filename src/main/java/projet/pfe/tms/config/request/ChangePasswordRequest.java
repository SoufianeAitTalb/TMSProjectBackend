package projet.pfe.tms.config.request;

public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmedPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword, String confirmedPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmedPassword = confirmedPassword;
    }

    public ChangePasswordRequest() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}