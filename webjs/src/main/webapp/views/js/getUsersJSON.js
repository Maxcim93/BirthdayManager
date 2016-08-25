function ajax_get(url, callback) {
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                //return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

ajax_get('/users', function(data) {
    if(data==null){
        document.getElementById("users").innerHTML = "<p>Users don't existed</p>";
        return;
    }
    var html= "<table>";
        html+="<tr>";
            html+="<th>ID</th>";
            html+="<th>Имя</th>";
            html+="<th>Номер</th>";
            html+="<th>Логин</th>";
            html+="<th>Друзья</th>";
        html+="</tr>";
        var size = data.length;
        for (var i=0;i!=size;++i) {
            html += "<tr>"+
            "<td>"+data[i].id+"</td>"+
            "<td>"+data[i].name+"</td>"+
            "<td>"+data[i].number+"</td>"+
            "<td>"+data[i].email+"</td>"+
            "<td>"+"<a href=\"views/view_friends.html?id="+data[i].id+"\"> Друзья пользователя</a>"+"</td>"+
            "</tr>"
        }
    html+="</table>";
    document.getElementById("users").innerHTML=html;
});