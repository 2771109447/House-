<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-profile page-account" id="page-top">
<div class="wrapper">

   <@common.nav/>

    <div id="page-content">
        <!-- Breadcrumb -->
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">Account</a></li>
                <li class="active">Profile</li>
            </ol>
        </div>

         <div class="container">
            <div class="row">
            <div class="col-md-3 col-sm-2">
                <section id="sidebar">
                    <header><h3>Account</h3></header>
                    <aside>
                        <ul class="sidebar-navigation">
                            <li><a href="/user/profile"><i class="fa fa-user"></i><span>个人信息</span></a></li>
                           <#if loginUser.type=2> <li  class="active" ><a href="/house/ownlist"><i class="fa fa-home"></i><span>我的房产信息</span></a></li></#if>
                            <li ><a href="/house/bookmarked"><i class="fa fa-heart"></i><span>房产收藏 </span></a></li>
                        </ul>
                    </aside>
                </section>
            </div>
                <div class="col-md-9 col-sm-10">
                    <section id="my-properties">
                        <header><h1>我的房产</h1></header>
                        <div class="my-properties">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>房产</th>
                                        <th></th>
                                        <th>创建时间</th>
                                        <th>类型</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                 <#list ps.list as house> 
                                    <tr>
                                        <td class="image">
                                            <a href="/house/detail?id=${house.id}"><img alt="" src="${house.firstImg}" style="width: 105px;height: 78px"></a>
                                        </td>
                                        <td><div class="inner">
                                            <a href="/house/detail?id=${house.id}"><h2>${house.name}</h2></a>
                                            <figure>${house.address}</figure>
                                            <div class="tag price">${house.price}</div>
                                        </div>
                                        </td>
                                        <td>${(house.createTime)?date}</td>
                                        <td><#if house.type==1>售卖<#else >出租</#if></td>
                                        <td><#if house.state==1>上架<#else>停售</#if></td>
                                        <td class="actions">
                                            <a href="/house/under?id=${house.id}"><i class="delete fa fa-trash-o"></i></a>
                                        </td>
                                    </tr>
                                 </#list>
                                </tbody>
                              </table>
                         </div>
                            <div class="center">
                                  <@common.paging ps.pagination/>
                            </div>
                        </div>
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