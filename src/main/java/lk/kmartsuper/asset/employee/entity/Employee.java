package lk.kmartsuper.asset.employee.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.kmartsuper.asset.branch.entity.Branch;
import lk.kmartsuper.asset.commonAsset.model.Enum.CivilStatus;
import lk.kmartsuper.asset.commonAsset.model.Enum.Gender;
import lk.kmartsuper.asset.commonAsset.model.Enum.Title;
import lk.kmartsuper.asset.commonAsset.model.FileInfo;
import lk.kmartsuper.asset.employee.entity.Enum.Designation;
import lk.kmartsuper.asset.employee.entity.Enum.EmployeeStatus;
import lk.kmartsuper.asset.message.entity.EmailMessage;
import lk.kmartsuper.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Employee" )
public class Employee extends AuditEntity {

    @Size( min = 5, message = "Your name cannot be accepted" )
    private String name;

    @Size( min = 3, message = "At least 5 characters should be included calling name" )
    @Column( unique = true )
    private String callingName;

    @Size( max = 12, min = 10, message = "NIC number must be contained numbers between 9 and X/V or 12 " )
    @Column( unique = true )
    private String nic;

    @Size( max = 10, min = 9, message = "Mobile number should be contained 10 and 9 digits" )
    private String mobileOne;

    private String mobileTwo;

    private String land;

    @Column( unique = true )
    private String email;

    @Column( unique = true )
    private String officeEmail;

    @Column( columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255 )
    private String address;

    @Enumerated( EnumType.STRING )
    private Title title;

    @Enumerated( EnumType.STRING )
    private Gender gender;

    @Enumerated( EnumType.STRING )
    private Designation designation;

    @Enumerated( EnumType.STRING )
    private CivilStatus civilStatus;

    @Enumerated( EnumType.STRING )
    private EmployeeStatus employeeStatus;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate dateOfBirth;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate dateOfAssignment;

    @ManyToOne
    private Branch branch;

    @ManyToMany( mappedBy = "employees" )
    private List<EmailMessage> emailMessages;

    @Transient
    private List< MultipartFile > files = new ArrayList<>();

    @Transient
    private List< String > removeImages = new ArrayList<>();

    @Transient
    private List<FileInfo> fileInfos = new ArrayList<>();

}
