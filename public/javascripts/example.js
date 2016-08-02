/**
 * Created by ariel7342 on 8/3/2016.
 */
var source1 = new EventSource("/sse/stream");
source1.addEventListener('message',function (e) {
   console.log(e.data);
});