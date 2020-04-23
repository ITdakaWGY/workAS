$(function () {

    var codeData = {};
    getCodeData(codeData);

    function getFileName(path) {
        var pos1 = path.lastIndexOf('/');
        var pos2 = path.lastIndexOf('\\');
        var pos = Math.max(pos1, pos2);
        if (pos < 0) {
            return path;
        } else {
            return path.substring(pos + 1);
        }
    }


    $('#apkWj').change(function () {
        var str = $(this).val();
        var appname = getFileName(str).split('_')[0];
        var appcode = getFileName(str).split('_')[1];
        var serverVersion = str.substring(str.lastIndexOf('_') + 1).split('.a')[0];
        /*$('#appname').val(appname);*/
        $('#appcode').val(appcode);
        $('#serverVersion').val(serverVersion);
    });

    var GUID = 'cc8273de15f64cf2a3e885fee90a4e3b';//一个GUID
    var uploader = WebUploader.create({
        // swf文件路径
        swf: 'js/Uploader.swf',
        // 文件接收服务端。
        server: './upload/part',
        accept: {
            extensions: "apk",
            mimeTypes: ".apk"
        },
        formData: {
            sysId: "cc8273de15f64cf2a3e885fee90a4e3b"
        },
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {id: "#picker", innerHTML: "选择上传文件"}, // 选择文件的按钮。可选
        chunked: true, // 分片处理
        chunkSize: 2 * 1024 * 1024, // 每片2M,
        chunkRetry: false,// 如果失败，则不重试
        threads: 1,// 上传并发数。允许同时最大上传进程数。
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    $("#ctlBtn").click(function () {
        uploader.upload();
    });

    uploader.on( 'fileQueued', function( file ) {
        var $list = $("#fileList"),
            $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
            ),
            $img = $li.find('img');


        // $list为容器jQuery实例
        $list.append( $li );

        // 创建缩略图
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, 100, 100 ); //100x100为缩略图尺寸
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css('width', percentage * 100 + '%');
    });
/*// 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                .appendTo( $li )
                .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });*/

    //当文件上传成功时触发。
    uploader.on("uploadSuccess", function (file) {
        var appname = $('#appname').val();
        var appcode = $('#appcode').val();
        var serverVersion = $('#serverVersion').val();
        var upgradeinfo = $('#miaoshu').val();
        var lastForce = $("input[name='lastForce']:checked").val();



        var params = {
            appname: appname,
            appcode: appcode,
            serverVersion: serverVersion,
            upgradeinfo: upgradeinfo,
            lastForce: lastForce
        };

        $.ajaxSettings.async = false;
        $.post('./upload/merge', {sysId: GUID, fileName: file.name}, function (data) {
            if (data.code == 200) {
                $.ajax({
                    type: "post",
                    url: './autoupdate/add/' + file.name ,
                    data: JSON.stringify(params),
                    contentType: "application/json",
                    async: false,
                    processData: false,
                    success: function (data) {
                        alert(data.message);
                    },
                    error: function (data) {
                        alert(data.message);
                    }
                })
            }
        });
    });

    //查询appname
    function getCodeData(data) {
        if(typeof(data) == "undefined") {
            data = {};
        }
        var result;
        $.ajax({
            type: "get",
            url: './main/list/1/100',
            data: data,
            async: true,
            success: function(data) {
                if(data.success) {
                    result = data.queryResult.list;
                    setCodedata(result);
                } else {
                    layer.msg(data.message);
                }

            }
        });
    }

    //接受结果集，往表格进行数据填充
    function setCodedata(result) {
        var form = layui.form;
       /* debugger;*/
        var html = '';
        html += `<option value="">请选择</option>`;
        for(var i = 0; i < result.length; i++) {
            html += `<option value="${result[i].appname}">${result[i].appname}</option>`;
        }
        $('#appname').html(html);
        getnull();
        form.render();

    }
    //如果页面有原始数据null 则置空
    function getnull() {
        $('.layui-table tr td').each(function () {
            var _this = $(this);
            if (_this.html() == 'null' || _this.html() == 'undefined') {
                _this.html('');
            }
        })
    }


})




