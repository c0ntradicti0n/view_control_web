package me.cc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import me.cc.model.AnyAnswer;
import me.cc.model.Tag;
import me.cc.model.annotationTagsFactory;

@ViewScoped
@ManagedBean(name = "commandBean")
public class CommandBean {
	CcPyBean ccPyBean;
	ArrayList<AnyAnswer> dummy_ans;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ccPyBean = (CcPyBean) facesContext.getApplication().createValueBinding("#{ccPyBean}").getValue(facesContext);
		dummy_ans = AnyAnswer.listFactory();

	}

	
	public String compAll() {
		ccPyBean.pycl.sglCall("recompute_all", "whatever");
		return "TrainScreen";
	}
	
	
	public String logs(String which) {
		ccPyBean.logs_which = which;
		ccPyBean.setLogs(ccPyBean.pycl.getLogs(which));
		return "LogScreen";
	};

	public String annotation_from_here() {
		ccPyBean.pycl.stdCall("annotation_from_here", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				ccPyBean.AnyAnswerList_Type);
		return "ReaderScreen";

	}

	public String take_it_as_is() {
		ccPyBean.pycl.stdCall("take_it_as_is", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				ccPyBean.AnyAnswerList_Type);
		return "ReaderScreen";

	}

	public String zero_annotation_selection_first_corpus() {
		ccPyBean.pycl.stdCall("zero_annotation_selection_first_corpus", ccPyBean.getSpot(), dummy_ans,
				ccPyBean.annotationSets, ccPyBean.AnyAnswerList_Type);
		return "ReaderScreen";

	}

	public String annotation_in_between() {
		ccPyBean.pycl.stdCall("annotation_in_between", ccPyBean.getSpot(), dummy_ans, ccPyBean.annotationSets,
				ccPyBean.AnyAnswerList_Type);
		return "ReaderScreen";

	}

	public String zero_annotation_selection_second_corpus() {
		ccPyBean.pycl.stdCall("zero_annotation_selection_second_corpus", ccPyBean.getSpot(), dummy_ans,
				ccPyBean.annotationSets, ccPyBean.AnyAnswerList_Type);
		return "ReaderScreen";
	}

	public String trash() {
		return "ReaderScreen";
	}

	public String migrateCorpus() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.pycl.sglCall("migrate_corpus", which));	
		return "TrainScreen";

		}

	public String mixCorpus() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.pycl.sglCall("mix_corpus", which));
		return "TrainScreen";

	}
	
	public String startTraining() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.pycl.sglCall("start_training", which));
		return "TrainScreen";

	}
	public String migrateModel() {
		String which = "this";
		ccPyBean.setLogs(ccPyBean.pycl.sglCall("migrate_model", which));	
		return "TrainScreen";

		}
	

	public String more_kind(String kind, int no) {
		System.out.println("add more " + kind + " set number " + no);

		int size_up_to_now = ccPyBean.annotationSets.get(no).size();
		ccPyBean.annotationSets.get(no).add(new Tag(no, size_up_to_now, kind, 0, ccPyBean.textlen - 1));
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);

		return "ManipulationScreen";

	}

	public String less_kind(int no, int _i, String kind) {
		System.out.println("delete " + kind + " set " + no + " index " + _i);
		ccPyBean.annotationSets.get(no).remove(_i);
		ccPyBean.annotationSets.removeIf(p -> p.isEmpty());
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);

		return "ManipulationScreen";

	}

	public String more_set(String kind) {
		System.out.println("add more " + kind + " as new set ");

		int size_up_to_now = ccPyBean.annotationSets.size();
		ArrayList<Tag> annotationSet = new ArrayList<Tag>();
		annotationSet.add(new Tag(size_up_to_now, 0, kind, 0, ccPyBean.textlen - 1));

		ccPyBean.annotationSets.add(annotationSet);
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);
		return "ManipulationScreen";
	}

	public String less_set(int no, int _i, String kind) {
		annotationTagsFactory.renumerate(ccPyBean.annotationSets);

		return "ManipulationScreen";

	}

}