function getSelectionCharacterOffsetWithin(element) {
    var start = 0;
    var end = 0;
    var doc = element.ownerDocument || element.document;
    var win = doc.defaultView || doc.parentWindow;
    var sel;
    if (typeof win.getSelection != "undefined") {
        sel = win.getSelection();
        if (sel.rangeCount > 0) {
            var range = win.getSelection().getRangeAt(0);
            var preCaretRange = range.cloneRange();
            preCaretRange.selectNodeContents(element);
            preCaretRange.setEnd(range.startContainer, range.startOffset);
            start = preCaretRange.toString().length;
            preCaretRange.setEnd(range.endContainer, range.endOffset);
            end = preCaretRange.toString().length;
        }
    } else if ( (sel = doc.selection) && sel.type != "Control") {
        var textRange = sel.createRange();
        var preCaretTextRange = doc.body.createTextRange();
        preCaretTextRange.moveToElementText(element);
        preCaretTextRange.setEndPoint("EndToStart", textRange);
        start = preCaretTextRange.text.length;
        preCaretTextRange.setEndPoint("EndToEnd", textRange);
        end = preCaretTextRange.text.length;
    }
    return { start: start, end: end };
}

function stripHtml(html)
{
   var tmp = document.createElement("DIV");
   tmp.innerHTML = html;
   return tmp.textContent || tmp.innerText || "";
}

function getBeforeAfterText(element, intervall, startend)  {
	var wt = stripHtml(element.textContent);
	alert(wt);
	text = wt.slice(startend.start, startend.end);
	b_start = startend.start-intervall<0 ? 0:startend.start-intervall
	a_end = startend.end+intervall >= wt.length? wt.length: startend.end+intervall
	before = wt.slice(b_start, startend.start);
	after = wt.slice(startend.end, a_end);
    return { text: text, before: before, after: after };

}

function  getSelectionText() {
  var selOffsets = getSelectionCharacterOffsetWithin( document.getElementById("text_window") );
  var bat = getBeforeAfterText(document.getElementById("text_window"), 100, selOffsets);
  document.getElementById("selectionLog").innerHTML = "Selection=" + JSON.stringify(bat) + "Selection offsets: " + selOffsets.start + ", " + selOffsets.end;
  return JSON.stringify(bat);
}