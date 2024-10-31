package markup;

import java.util.List;

class Emphasis implements MarkupElement {
    private final List<MarkupElement> elements;

    public Emphasis(List<MarkupElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append('*');
        for (MarkupElement element : elements) {
            element.toMarkdown(sb);
        }
        sb.append('*');
    }

    @Override
    public void toTypst(StringBuilder sb) {
        sb.append("#emph[");
        for (MarkupElement element : elements) {
            element.toTypst(sb);
        }
        sb.append("]");
    }
}