<!DOCTYPE html>

<html lang="en-US">
 <@common.header/>

<body class="page-sub-page page-agent-detail" id="page-top">
<div class="wrapper">
     <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">经纪人</a></li>
                <li class="active">经纪人详情</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="agent-detail">
                        <header><h1>${agent.name!}</h1></header>
                        <section id="agent-info">
                            <div class="row">
                                <div class="col-md-3 col-sm-3">
                                    <figure class="agent-image"><img alt="" src="${(agent.avatar)!}"></figure>
                                </div>
                                <div class="col-md-5 col-sm-5">
                                    <h3>联系信息</h3>
                                    <dl>
                                        <dt>手机:</dt>
                                        <dd>${(agent.phone)!}</dd>
                                        <dt>Email:</dt>
                                        <dd><a href="mailto:#">${(agent.email)!}</a></dd>
                                        <dt>经纪机构:</dt>
                                        <dd>${(agent.agencyName)!}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <h3>简单自我介绍</h3>
                                    <p>${agent.aboutme!}
                                    </p>
                                </div>
                            </div>
                            
                        </section>
                        <hr class="thick">
                        <section id="agent-properties">
                            <header><h3>我代理的房产</h3></header>
                            <div class="layout-expandable">
                                <div class="row">
                                  <#list bindHouses as house>
                                    <div class="col-md-4 col-sm-4">
                                        <div class="property">
                                            <a href="/house/detail?id=${house.id}">
                                                <div class="property-image">
                                                    <img alt="" src="/static/assets/img/properties/property-09.jpg">
                                                </div>
                                                <div class="overlay">
                                                    <div class="info">
                                                        <div class="tag price">￥ ${house.price}</div>
                                                        <h3>${house.name}</h3>
                                                        <figure>${house.address}</figure>
                                                    </div>
                                                    <ul class="additional-info">
                                                        <li>
                                                            <header>面积:</header>
                                                            <figure>${house.area}<sup>2</sup></figure>
                                                        </li>
                                                        <li>
                                                            <header>床:</header>
                                                            <figure>${house.beds}</figure>
                                                        </li>
                                                        <li>
                                                            <header>洗手间:</header>
                                                            <figure>${house.baths}</figure>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                   </#list>
                                </div>
                               
                            </div>
                           
                        </section>
                        <hr class="thick">
                        <div class="row">
                            <div class="col-md-5">
                                <section id="agent-testimonials">
                                    <h3>有其他想和我说的吗？</h3>
                                    <div class="owl-carousel testimonials-carousel small">
                                        <blockquote class="testimonial">
                                            <figure>
                                                <div class="image">
                                                    <img alt="" src="/static/static/assets/img/client-01.jpg">
                                                </div>
                                            </figure>
                                            <aside class="cite">
                                                <p>Fusce risus metus, placerat in consectetur eu, porttitor a est sed sed dolor lorem cras adipiscing</p>
                                                <footer>Natalie Jenkins</footer>
                                            </aside>
                                        </blockquote>
                                        <blockquote class="testimonial">
                                            <figure>
                                                <div class="image">
                                                    <img alt="" src="/static/static/assets/img/client-01.jpg">
                                                </div>
                                            </figure>
                                            <aside class="cite">
                                                <p>Fusce risus metus, placerat in consectetur eu, porttitor a est sed sed dolor lorem cras adipiscing</p>
                                                <footer>Natalie Jenkins</footer>
                                            </aside>
                                        </blockquote>
                                    </div>
                                </section>
                            </div>
                            <div class="col-md-7">
                                <h3>给我留言</h3>
                                <div class="agent-form">
                                    <form role="form" id="form-contact-agent" method="post"  class="clearfix" action="/agency/leaveMsg">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form-contact-agent-name">你的姓名<em>*</em></label>
                                                    <input type="text" class="form-control" id="form-contact-agent-name"  name="name" required>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="form-contact-agent-email">你的邮箱<em>*</em></label>
                                                    <input type="email"  class="form-control" id="form-contact-agent-email" name="email" required>
                                                </div>
                                            </div>
                                        </div>
                                        <input type="hidden" name="id" value="${agent.id!}">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label for="form-contact-agent-message">你的留言<em>*</em></label>
                                                    <textarea class="form-control" id="form-contact-agent-message" rows="5" name="msg" required></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group clearfix">
                                            <button type="submit" class="btn pull-right btn-default" id="form-contact-agent-submit">Send a Message</button>
                                        </div>
                                        <div id="form-rating-status"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>

                <div class="col-md-3 col-sm-3">
                    <section id="sidebar">
                     <@common.search />
                     <@common.hot />
                    </section>
                </div>
            </div>
        </div>
    </div>

        <@common.footer/>
</div>

<@common.js/>
<script type="text/javascript" src="/assets/js/ie.js"></script>
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