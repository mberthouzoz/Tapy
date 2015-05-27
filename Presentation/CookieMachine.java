// Prototype Class
class Cookie implements Cloneable {
    @Override
    public Cookie clone() {
        try {
            Cookie copy = (Cookie) super.clone();
            // Dans une implémentation réelle de ce patron de conception, il faudrait
            // créer la copie en dupliquant les objets contenus et en attribuant des
            // valeurs valides (exemple : un nouvel identificateur unique pour la copie).
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

// Concrete Prototype to clone
class ChocolateCookie extends Cookie {
    public String toString() {
        return "Cookie Chocolat";
    }
}

// Client Class
public class CookieMachine {

    private Cookie cookie;

    public CookieMachine(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie makeCookie() {
        return cookie.clone();
    }

    public static void main(String args[]) {
        Cookie tempCookie = null;
        Cookie prot = new ChocolateCookie();
        CookieMachine cm = new CookieMachine(prot);

        for (int i = 0; i < 100; i++) {
            tempCookie = cm.makeCookie();
            System.out.println(tempCookie);
        }
    }
}
