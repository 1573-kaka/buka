<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>留言板</title>
</head>
<body>
    <%--<form action="" method="post" class="STYLE-NAME">
        <p>用户名<input type="text" name="username"></p>
        <p>留言板<textarea name="content"></textarea></p>
        <p><input type="submit" value="留言"></p>
    </form>--%>
    <section id="contactUs" class="page-section secPad">
        <div class="container">
            <!--下面是留言样式 -->
            <div class="row">
                <div class="headingMessage">
                    <!-- Heading -->
                    <h2>请在这里写对本平台的反馈</h2>
                </div>
            </div>

            <div class="row mrgn30">
                <div class="col-sm-12 col-md-8">
                    <!--NOTE: Update your email Id in "contact_me.php" file in order to receive emails from your contact form-->
                    <form action="${pageContext.request.contextPath}/feedback/edit.do" name="sentMessage" id="contactForm" method="post" novalidate>
                        <h3>反馈</h3>
                        <div class="control-group">
                            <div class="controls">
                                <input type="hidden" id="sendMail" name="sendMail" value="${loginUser.email}">
                                <input name="sendPer" type="hidden" value="${sessionScope.loginUser.userName}" />
                                <p class="help-block"></p>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
<textarea name="content" rows="10" cols="100" class="form-control"
          placeholder="留言内容" id="message" required
          data-validation-required-message="Please enter your message" minlength="5"
          data-validation-minlength-message="Min 5 characters"
          maxlength="999" style="resize:none"></textarea>
                            </div>
                        </div>
                        <div id="success"> </div>
                        <button type="submit" class="btn btn-primary pull-right">提交</button><br /><span>${tips}</span>
                    </form>
                </div>
            </div>
        </div>
    </section>
</form>
</body>
</html>
