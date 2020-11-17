# Pretty Clean Architecture 

## Tại sao cần sử dụng các mẫu kiến trúc trong phát triển phần mềm
Tất cả các kiến trúc đều có một mục tiêu chung - quản lý mức độ phức tạp về code trong ứng dụng của bạn. 
<br>Bạn có thể không cần phải lo lắng về nó trong một dự án nhỏ, nhưng nó sẽ trở thành lifesaver cho những dự án lớn hơn.

<br>Có thể bạn đã nhìn thấy biểu đồ này ở đâu đó rồi  
![Alt text](https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png)

Hình tròn này biểu diễn các tầng khác nhau trong ứng dụng. Có 2 ý chính cần lưu ý
* Vòng tròn trung tâm là trừu tượng nhất, và vòng tròn bên ngoài là cụ thể nhất. Đây được gọi là __Abstraction Principle__ (nguyên lý trừu tượng). Nguyên lý này nói rằng các vòng tròn bên trong sẽ chứa nghiệp vụ logic __( business logic )__, và các vòng tròn bên ngoài sẽ triển khai chi tiết __(implementation details)__.
* 1 nguyên tắc khác của Clean Architecture là __dependency rule__. Quy tắc này qui định rằng mỗi vòng tròn chỉ có thể phụ thuộc vào vòng tròn bên trong gần nhất

Các lợi ích của việc sử dụng clean architecture 
* Các phần code được tách ra và dễ dàng tái sử dụng và testing
* Khi người khác làm việc với code này, họ có thể tìm hiểu Clean Architecture và sẽ hiểu rõ hơn về nó.

## SOLID Principles
Nắm vững những nguyên lý này, đồng thời áp dụng chúng trong việc thiết kế + viết code sẽ giúp bạn tiến thêm 1 bước trên con đường thành senior nhé (ông toidicodedao bảo thế).
_5 nguyên tắc thiết kế giúp thiết kế phần mềm dễ hiểu, linh hoạt và dễ bảo trì hơn_ 
* __Single Responsibility__:  Một class chỉ nên giữ 1 trách nhiệm duy nhất (Chỉ có thể sửa đổi class với 1 lý do duy nhất)
* __Open/closed__: Có thể thoải mái mở rộng 1 class, nhưng không được sửa đổi bên trong class đó (open for extension but closed for modification).
* __Liskov Substitution__: Trong một chương trình, các object của class con có thể thay thế class cha mà không làm thay đổi tính đúng đắn của chương trình
* __Interface Segregation__: Thay vì dùng 1 interface lớn, ta nên tách thành nhiều interface nhỏ, với nhiều mục đích cụ thể
* __Dependency Inversion__: 
<br>1. Các module cấp cao không nên phụ thuộc vào các modules cấp thấp. Cả 2 nên phụ thuộc vào abstraction.
<br>2. Interface (abstraction) không nên phụ thuộc vào chi tiết, mà ngược lại. (Các class giao tiếp với nhau thông qua interface, không phải thông qua implementation)

<br>__Clean Architecture sử dụng tối đa các nguyên tắc này.__

## Các layer của Clean Architecture
Có nhiều ý kiến khác nhau về việc nên có bao nhiêu layer Clean Architecture. Mô hình này không quy định chính xác bao nhiêu layer mà thay vào đó là đặt ra nền tảng và bạn chính là người điều chỉnh số lượng layer trong ứng dụng của mình.
<br>Ở đây, để đơn giản nhất có thể, chúng ta sử dụng 2 module chính với tổng cộng 5 layer
<br>__Business Module__
* __Domain__: chứa các model của app
* __Data__: định nghĩa trừu tượng của tất cả các nguồn dữ liệu.
* __Interactors__: còn được gọi là Use case. Xác định các hành động mà người dùng có thể kích hoạt.

__Framework Module__
* __Presentation__: giao diện người dùng
* __Datasource__: Triển khai và phát triển cụ thể cho lớp Data

![Alt text](https://camo.githubusercontent.com/b1521f6c9e672cf5077ba69ceab27679ea8a82074a89931d958895476756e2c8/68747470733a2f2f636f64696e67776974686d697463682e73332e616d617a6f6e6177732e636f6d2f7374617469632f636f75727365732f32312f636c65616e5f6172636869746563747572655f6469616772616d732e706e67)

## Release
__17//11/2020__: Thêm sample project sử dụng Clean Architecture, Coroutines, Flow, Hilt, Navigation Components 

## Credits
1. [https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)
2. [https://github.com/mitchtabian/Clean-Notes](https://github.com/mitchtabian/Clean-Notes)
3. [https://proandroiddev.com/kotlin-clean-architecture-1ad42fcd97fa](https://proandroiddev.com/kotlin-clean-architecture-1ad42fcd97fa)
4. [https://toidicodedao.com/2015/03/24/solid-la-gi-ap-dung-cac-nguyen-ly-solid-de-tro-thanh-lap-trinh-vien-code-cung/](https://toidicodedao.com/2015/03/24/solid-la-gi-ap-dung-cac-nguyen-ly-solid-de-tro-thanh-lap-trinh-vien-code-cung/)













