	function getSelectionText(around = 100) {
	    var text = "";
	    var activeEl = document.activeElement;
	    var activeElTagName = activeEl ? activeEl.tagName.toLowerCase() : null;
	    


	    
	    

	    if (
	      (activeElTagName == "textarea") || (activeElTagName == "input" &&
	      /^(?:text|search|password|tel|url)$/i.test(activeEl.type)) &&
	      (typeof activeEl.selectionStart == "number")
	    ) {
	    	before = activeEl.value.slice(activeEl.selectionStart-around, activeEl.selectionStart)
	        text = activeEl.value.slice(activeEl.selectionStart, activeEl.selectionEnd);
	    	after = activeEl.value.slice(activeEl.selectionEnd-around, activeEl.selectionEnd)
	    } else if (window.getSelection) {
		    
		    var startIndex = window.getSelection().getRangeAt(0).startOffset;
		    var endIndex = window.getSelection().getRangeAt(0).endOffset;
		    
		    var windowtext = window.getSelection().anchorNode.nodeValue;
		    var text = window.getSelection().toString().slice(startIndex, endIndex);
		    
	    	before = window.getSelection().toString().slice(startIndex-around, startIndex);
	    	after = window.getSelection().toString().slice(endIndex, endIndex+around);
	    	
		    alert  (activeElTagName +startIndex+ " "+ endIndex + + " wint= " + windowtext+ " b= " + before + " t= " + text + " a= " + after);

	    }
	    return [before, text, after];
	}

function ShowSelectionInsideTextarea() {
	var textComponent = document.getElementById('text_window');
    var selectedText = getSelectionText();

	alert("You selected: " + selectedText);

}