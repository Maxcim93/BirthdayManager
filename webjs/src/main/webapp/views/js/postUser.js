function sendJSON(url,object){
    var xhr = new XMLHttpRequest();

    xhr.open("POST", url, true);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(object);

    if (xhr.readyState == 4 && xhr.status == 200) {
        console.log('responseOnPostUser:' + xhr.responseText);
    }
}
function sendUser(){
    var jsonUser = JSON.stringify({
      name: document.getElementById("name").value,
      number: document.getElementById("number").value,
      email: document.getElementById("email").value
    });

    sendJSON("/users",jsonUser);
}