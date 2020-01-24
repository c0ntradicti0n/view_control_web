m = new Mark("#text_window")

function highlight_annotation (str)  {
    console.log("marking " + str)
    console.log(m.mark(str, {
        "element": "span",
        "className": "highlighted",
        "accuracy": "exactly",
        "separateWordSearch": false,
        "acrossElements": true,
        "ignorePunctuation":  [    "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
                                   "-", "_", "=", "+", "{", "}", "[", "]", "\\", "|", ":", ";",
                                   "'", "\"", ",", ".", "<", ">", "/", "?",
                                  " "
                             ]
    }));
    }


window.new_annotations = ""
function mark_what_was_recently_annotated(str_liste)  {
    try {
        window.new_annotations = str_liste;
        let liste = str_liste.split("`~`");
        liste.forEach((element) => highlight_annotation(element));
        }
    catch (e) {
        let liste = str_liste.split("`~`");
        console.log(liste);
        console.log("could not unpack list");
    }
}
