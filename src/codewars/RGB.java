package codewars;

final class RGB {

    public int r, g, b;

    public RGB() {}

    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RGB) {
            RGB rgb = (RGB) obj;
            return (r == rgb.r) && (g == rgb.g) && (b == rgb.b);
        }
        return false;
    }
}
