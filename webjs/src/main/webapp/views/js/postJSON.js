function sendJSON(url,object, callback){
            var xhr = new XMLHttpRequest();

            xhr.open("POST",url, true);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(object);

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    console.log('responseText:' + xhr.responseText);
                    callback(xhr.responseText);
                }
            };
        }