package by.itacademy.core;

public final class Constants {

    public static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)" +
            "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    public static final String UUID_PATTERN =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-4[a-fA-F0-9]{3}-[89abAB][a-fA-F0-9]{3}-[a-fA-F0-9]{12}";

    private Constants() {
    }
}
