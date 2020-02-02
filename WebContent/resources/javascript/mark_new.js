function nomatch() {
    console.log("not matched");
}

function highlight_annotation(str) {
    m = new Mark((document.querySelector("body")))
    console.log("marking " + str);
    m.mark(str, {
        "element": "span",
        "className": "highlighted",
        "accuracy": "exactly",
        "noMatch": nomatch,
        "separateWordSearch": false,
        "acrossElements": true,
        "ignorePunctuation": ["`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
            "-", "_", "=", "+", "{", "}", "[", "]", "\\", "|", ":", ";", "’",
            "'", "\"", ",", ".", "<", ">", "/", "?",
            " "
        ]
    });
}


window.new_annotations = ""

function mark_what_was_recently_annotated(str_liste) {
        window.new_annotations = str_liste;
        let liste = str_liste.split("`~`");
        liste.forEach((element) => highlight_annotation(element));

}
