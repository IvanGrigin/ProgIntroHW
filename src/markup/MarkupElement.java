package markup;


interface MarkupElement {
    void toMarkdown(StringBuilder sb);
    void toTypst(StringBuilder sb);
}