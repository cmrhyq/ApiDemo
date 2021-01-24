<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Version</title>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
    <style>
        #tagTime {
            margin-right: 1em;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Success</h1>
    <h4>
        <strong id="tagTime">Now Time</strong>
        <span id="dateTime"></span>
    </h4>
    <h5>
        <strong id="tagTime">Request Time</strong>
        <span>${request_time}</span>
    </h5>
    <div>${request}</div>
</div>
</body>
<script>
    Date.prototype.format = function (fmt) {
        let o = {
            "y+": this.getFullYear, //年
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds() //秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (let k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    setInterval("document.getElementById('dateTime').innerHTML = (new Date()).format('yyyy-MM-dd hh:mm:ss');", 1000);
</script>
</html>