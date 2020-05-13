<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-create-agency page-account" id="page-top">
<div class="wrapper">
     <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li class="active">创建经纪机构</li>
            </ol>
        </div>

        <div class="container">
            <header><h1>创建经纪机构</h1></header>
            <div class="row">
                <div class="col-md-8 col-sm-12 col-md-offset-2">
                    <form role="form" id="form-create-agency" method="post" action="/agency/submitAgency">
                        <section>Terms and conditions
                            <div class="form-group">
                                <label for="form-create-agency-title">经纪机构名称:</label>
                                <input type="text" class="form-control" id="form-create-agency-title" name="name" required>
                            </div>
                            <div class="form-group">
                                <label for="form-create-agency-description">描述:</label>
                                <textarea class="form-control" id="form-create-agency-description"    name="aboutUs" rows="4" required></textarea>
                            </div>
                        </section>
                        <h3>联系信息</h3>
                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <section id="address">
                                    <div class="form-group">
                                        <label for="form-create-agency-address-1">地址:</label>
                                        <input type="text" class="form-control" name="address" id="form-create-agency-address-1" required>
                                    </div>
                                </section>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <section id="contacts">
                                    <div class="form-group">
                                        <label for="form-create-agency-email">Email:</label>
                                        <input type="email" class="form-control" name="email" id="form-create-agency-email" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="form-create-agency-phone">手机:</label>
                                        <input type="tel" class="form-control"   name="phone" id="form-create-agency-phone">
                                    </div>

                                    <div class="form-group">
                                        <label for="form-create-agency-phone">电话:</label>
                                        <input type="tel" class="form-control"   name="mobile" id="form-create-agency-phone">
                                    </div>
                                    <div class="form-group">
                                        <label for="form-create-agency-website">网站:</label>
                                        <input type="text" class="form-control"  name="webSite" id="form-create-agency-website">
                                    </div>

                                </section>
                            </div>
                        </div>
                        <section id="submit">
                            <div class="form-group center">
                                <button type="submit" class="btn btn-default large" id="account-submit">创建经纪机构信息</button>
                            </div>
                        </section>
                    </form>
                    <hr>
                    <section class="center">
                        <figure class="note">点击"创建经济机构信息"按钮,你必须同意"<a href="terms-conditions.html">条款</a></figure>
                    </section>
                </div>
            </div>
        </div>
    </div>

   <@common.footer/>
</div>

<@common.js/>
<script type="text/javascript" src="assets/js/ie.js"></script>
 <script  type="text/javascript" >
     

     $(document).ready(function() {
          var errorMsg   = "${errorMsg!""}";
          var successMsg = "${successMsg!""}";
          if(errorMsg){ 
              errormsg("error",errorMsg);
          }
          if(successMsg) {
              successmsg("success",successMsg);
          }
        })
        
 </script>

</body>
</html>