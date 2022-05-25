package curcul.domain;

public enum MenuItem {
    BOARDGAMES("Настольные игры"), ADDONS("Дополнения"), WARHAMMER("Warhammer"),
    KKILCD("ККИ/ЖКИ"), ROLEPLAYINGGAMES("Ролевые игры"), NEW("Новинки"),
    PREORDERS("Предзаказы"), STOCK("Акции");
    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}

