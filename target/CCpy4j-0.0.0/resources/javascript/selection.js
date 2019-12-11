	function getSelectionText() {
	    var text = "";
	    var activeEl = document.activeElement;
	    var activeElTagName = activeEl ? activeEl.tagName.toLowerCase() : null;
	    if (
	      (activeElTagName == "textarea") || (activeElTagName == "input" &&
	      /^(?:text|search|password|tel|url)$/i.test(activeEl.type)) &&
	      (typeof activeEl.selectionStart == "number")
	    ) {
	        text = activeEl.value.slice(activeEl.selectionStart, activeEl.selectionEnd);
	    } else if (window.getSelection) {
	        text = window.getSelection().toString();
	    }
	    alert( text);
        //var inp = document.getElementById('xorm:kind');
        //inp.value = text
	    //document.getElementById('kind').value = text
        //alert (document.getElementById('kind').value);
	    return text;
	}

function ShowSelectionInsideTextarea() {
	var textComponent = document.getElementById('text_window');
    var selectedText = getSelectionText();

	alert("You selected: " + selectedText);

}