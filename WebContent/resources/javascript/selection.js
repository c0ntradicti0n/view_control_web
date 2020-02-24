function  getSelectionText() {
    const chars_around = 300;
    var text = window.getSelection().toString();

    var text_before = "";
    var text_after = "";

    return JSON.stringify({text: text, before: text_before, after:text_after})
}