package markup;

import java.util.List;

class Strong implements MarkupElement {
    private final List<MarkupElement> elements;

    public Strong(List<MarkupElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        for (MarkupElement element : elements) {
            element.toMarkdown(sb);
        }
        sb.append("__");
    }

    @Override
    public void toTypst(StringBuilder sb) {
        sb.append("#strong[");
        for (MarkupElement element : elements) {
            element.toTypst(sb);
        }
        sb.append("]");
    }
}