package markup;

import java.util.List;

class Paragraph implements MarkupElement {
    private final List<MarkupElement> elements;

    public Paragraph(List<MarkupElement> elements) {
        this.elements = elements;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (MarkupElement element : elements) {
            element.toMarkdown(sb);
        }
    }

    @Override
    public void toTypst(StringBuilder sb) {
        for (MarkupElement element : elements) {
            element.toTypst(sb);
        }
    }

    public static void main(String[] args) {
        Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));

        StringBuilder markdown = new StringBuilder();
        paragraph.toMarkdown(markdown);
        System.out.println("Markdown: \n" + markdown.toString());

        StringBuilder typst = new StringBuilder();
        paragraph.toTypst(typst);
        System.out.println("Typst: \n" + typst.toString());

    }
}

